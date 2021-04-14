package v1.br.com.pbsoft.services

import com.google.inject.ImplementedBy
import play.api.http.Status
import play.api.libs.json.{JsError, JsSuccess}
import play.api.libs.ws.WSClient
import javax.inject.Inject
import play.api.libs.ws._
import v1.br.com.pbsoft.domain.DomainModel.{GitContributors, GitRepository}
import v1.br.com.pbsoft.services.GithubService.GITHUB_API_BASE_URL
import scala.concurrent.{ExecutionContext, Future}

@ImplementedBy(classOf[GithubService])
trait GitService {
    def listContributionsByOrganizations(orgName: String): Future[Seq[GitContributors]]
}

class GithubService @Inject()(ws: WSClient) (implicit ec: ExecutionContext) extends GitService {

   private def getRepositoriesByOrg(orgName: String): Future[Seq[GitRepository]] = {
    val url = s"$GITHUB_API_BASE_URL/orgs/$orgName/repos"
    val wsRequest: Future[WSResponse] = ws.url(url).get()

    wsRequest.map{ resp =>
      resp.status match {
        case Status.OK => {
          val validatedJson = resp.json.validate[Seq[GitRepository]]
          validatedJson match {
            case JsSuccess(_, _) => resp.json.as[Seq[GitRepository]]
            case e: JsError      => throw new GitServiceException(s"Erro no parse do json para Seq[GitRepository]")
          }
        }
        case _ => throw new GitServiceException("Falha ao consultar repositorios")
      }
    }
  }

  private def getContributionsByRepository(orgName: String, repos: String ): Future[Seq[GitContributors]] = {
    val url = s"$GITHUB_API_BASE_URL/repos/$orgName/$repos/contributors"
    val wsRequest: Future[WSResponse] = ws.url(url).get()

    wsRequest.map{ resp =>
      resp.status match {
        case Status.OK => {
          val validatedJson = resp.json.validate[Seq[GitContributors]]
          validatedJson match {
            case JsSuccess(_, _) => resp.json.as[Seq[GitContributors]]
            case e: JsError      => throw new GitServiceException(s"Erro no parse do json para Seq[GitRepository]")
          }
        }
        case _ => throw new GitServiceException(s"Falha ao consultar contribuições - orgname: ${orgName}; repos: ${repos}")
      }
    }
  }

  def listContributionsByOrganizations(orgName: String): Future[Seq[GitContributors]] = {
    val repositories: Future[Seq[GitRepository]] = getRepositoriesByOrg(orgName)
    val contributors: Future[Seq[Seq[GitContributors]]] = repositories.flatMap { repositories =>
      Future.traverse(repositories)(repository => getContributionsByRepository(orgName, repository.name))
    }
    val result: Future[Seq[GitContributors]] = contributors.map{ contrib => contrib
     .flatten
     .groupBy(_.login)
     .toSeq
     .map{ tupla =>
       GitContributors(
         login = tupla._1,
         contributions = tupla._2.map(_.contributions).sum
       )
     }
    }
    result
  }
}

class GitServiceException(str: String) extends Exception(str)

object GithubService {
  final val GITHUB_API_BASE_URL = "https://api.github.com"
}