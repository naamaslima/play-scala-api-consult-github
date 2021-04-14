// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:1
package v1.br.com.pbsoft.controllers.javascript {

  // @LINE:1
  class ReverseContributorsController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:1
    def listByOrgs: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "v1.br.com.pbsoft.controllers.ContributorsController.listByOrgs",
      """
        function(orgName0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/organizations/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("orgName", orgName0)) + "/contributors"})
        }
      """
    )
  
  }


}
