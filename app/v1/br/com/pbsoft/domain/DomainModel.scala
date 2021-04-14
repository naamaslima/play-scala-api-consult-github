package v1.br.com.pbsoft.domain

import play.api.libs.json.{ Json, Reads, Writes}

object DomainModel {

  case class GitRepository(name: String)
  case class GitContributors(login: String, contributions: Int)

  implicit val gitRepositoriesReads: Reads[GitRepository] = Json.reads[GitRepository]
  implicit val gitRepositoriesWrites: Writes[GitRepository] = Json.writes[GitRepository]

  implicit val gitRepositoriesListReads: Reads[Seq[GitRepository]] = Reads.seq[GitRepository]
  implicit val gitRepositoriesListWrites: Writes[Seq[GitRepository]] = Writes.seq[GitRepository]

  implicit val gitContributionsReads: Reads[GitContributors] = Json.reads[GitContributors]
  implicit val gitContributionsWrites: Writes[GitContributors] = Json.writes[GitContributors]

  implicit val gitContributionsListReads: Reads[Seq[GitContributors]] = Reads.seq[GitContributors]
  implicit val gitContributionsListWrites: Writes[Seq[GitContributors]] = Writes.seq[GitContributors]

}
