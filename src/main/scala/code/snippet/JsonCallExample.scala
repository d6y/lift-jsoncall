package code.snippet
import net.liftweb.http.SHtml
import net.liftweb.http.js.JE.JsRaw
import net.liftweb.util._
import net.liftweb.util.Helpers._
import net.liftweb.http.js.JsCmds
import net.liftweb.http.js.JE
import net.liftweb.http.DispatchSnippet

import net.liftweb.json.JsonAST.JValue
import net.liftweb.http.js.JsCmds

class JsonCallExample extends DispatchSnippet {
 
  def dispatch = {
    case "working" => workingExample
    case "notworking" => notWorkingExample
  }
  
  def workingExample = {
    val function = SHtml.jsonCall(JsRaw("[1329652277766,1329652277766]"), (s:JValue) => { println(s); JsCmds.Noop })
    
    "#foobar-working" #> JsCmds.Script(JE.JsRaw("""
        $(function() { $('#button-example-working').bind('click', function() {
          """ + function._2.toJsCmd + """ 
        })});""").cmd)
  }
  
  def notWorkingExample = {
    val function = SHtml.jsonCall(JsRaw("[1329652277766,1329652277766.118]"), (s:JValue) => { println(s); JsCmds.Noop })
    
    "#foobar" #> JsCmds.Script(JE.JsRaw("""
        $(function() { $('#button-example').bind('click', function() {
          """ + function._2.toJsCmd + """ 
        })});""").cmd)
  }  
}