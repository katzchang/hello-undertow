package hello

import io.undertow._
import server.{HttpServerExchange, HttpHandler}
import util.Headers

object Hello {
  def main(args: Array[String]) = {
    val server: Undertow = Undertow.builder().addListener(8080, "localhost").setHandler(new HttpHandler() {
      def handleRequest(exchange: HttpServerExchange) = {
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain")
        exchange.getResponseSender().send("Hello World")
      }
    }).build()
    server.start()
  }
}
