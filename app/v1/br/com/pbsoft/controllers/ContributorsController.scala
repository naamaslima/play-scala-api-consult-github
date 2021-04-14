package v1.br.com.pbsoft.controllers

import javax.inject.Inject
import play.api.libs.json.Json.toJson
import scala.concurrent.ExecutionContext
import play.api.mvc._
import v1.br.com.pbsoft.services.GitService

/**
  * Takes HTTP requests and produces JSON.
  */
class ContributorsController @Inject()(gitService: GitService, cc: ControllerComponents) (
  implicit ec: ExecutionContext)
  extends AbstractController(cc) {

  def listByOrgs(orgName: String) = Action.async{
    gitService.listContributionsByOrganizations(orgName)
      .map{ repositories =>
        Ok(toJson(repositories))
      }.recover{ error =>
      BadRequest(error.getMessage)
    }
  }
}
