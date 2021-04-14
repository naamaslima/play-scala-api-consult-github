// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:1
  ContributorsController_0: v1.br.com.pbsoft.controllers.ContributorsController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:1
    ContributorsController_0: v1.br.com.pbsoft.controllers.ContributorsController
  ) = this(errorHandler, ContributorsController_0, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, ContributorsController_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/v1/organizations/""" + "$" + """orgName<[^/]+>/contributors""", """v1.br.com.pbsoft.controllers.ContributorsController.listByOrgs(orgName:String)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:1
  private[this] lazy val v1_br_com_pbsoft_controllers_ContributorsController_listByOrgs0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/v1/organizations/"), DynamicPart("orgName", """[^/]+""",true), StaticPart("/contributors")))
  )
  private[this] lazy val v1_br_com_pbsoft_controllers_ContributorsController_listByOrgs0_invoker = createInvoker(
    ContributorsController_0.listByOrgs(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "v1.br.com.pbsoft.controllers.ContributorsController",
      "listByOrgs",
      Seq(classOf[String]),
      "GET",
      this.prefix + """api/v1/organizations/""" + "$" + """orgName<[^/]+>/contributors""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:1
    case v1_br_com_pbsoft_controllers_ContributorsController_listByOrgs0_route(params@_) =>
      call(params.fromPath[String]("orgName", None)) { (orgName) =>
        v1_br_com_pbsoft_controllers_ContributorsController_listByOrgs0_invoker.call(ContributorsController_0.listByOrgs(orgName))
      }
  }
}
