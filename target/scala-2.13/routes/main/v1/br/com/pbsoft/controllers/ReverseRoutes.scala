// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:1
package v1.br.com.pbsoft.controllers {

  // @LINE:1
  class ReverseContributorsController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:1
    def listByOrgs(orgName:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "api/v1/organizations/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("orgName", orgName)) + "/contributors")
    }
  
  }


}
