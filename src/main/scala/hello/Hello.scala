package hello

import io.undertow._
import server.{HttpServerExchange, HttpHandler}
import util.Headers

object Hello extends App {
  override def main(args: Array[String]) = {
    val server: Undertow = Undertow.builder().addListener(8080, "localhost").setHandler(new HttpHandler() {
      def handleRequest(exchange: HttpServerExchange) = {
        println("exchange.getPathParameters" -> exchange.getPathParameters)
        println("exchange.getHostAndPort" -> exchange.getHostAndPort)
        println("exchange.getQueryParameters" -> exchange.getQueryParameters)
        println("exchange.getQueryString" -> exchange.getQueryString)
        println("exchange.getRequestURL" -> exchange.getRequestURL)
        println("exchange.getRequestURI" -> exchange.getRequestURI)

        //        http://localhost:8080/foo/bar?fuga=hoge
        //        (exchange.getPathParameters,{})
        //        (exchange.getHostAndPort,localhost:8080)
        //        (exchange.getQueryParameters,{fuga=[hoge]})
        //        (exchange.getQueryString,fuga=hoge)
        //        (exchange.getRequestURL,http://localhost:8080/foo/bar)
        //        (exchange.getRequestURI,/foo/bar)
        exchange.dispatch(HelloHandler)
      }
    }).build()
    server.start()
  }
}

object PlainTextHandler extends HttpHandler {
  def handleRequest(exchange: HttpServerExchange) = {
    exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain")
  }
}

object HelloHandler extends HttpHandler {
  def handleRequest(exchange: HttpServerExchange) = {
    exchange.dispatch(PlainTextHandler)
    exchange.getResponseSender().send("Hello World")
  }
}
