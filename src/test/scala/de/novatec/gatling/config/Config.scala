package de.novatec.gatling.config

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration._

class Configmaster {
  val usersAtOnce: Int = sys.env.getOrElse("GATLING_NR_USERS_AT_ONCE", "2").toInt
  val users: Int = sys.env.getOrElse("GATLING_NR_USERS", "10").toInt
  val maxDuration: FiniteDuration = sys.env.getOrElse("GATLING_MAX_DURATION", "2").toInt minutes
  val rampUpTime: FiniteDuration = sys.env.getOrElse("GATLING_RAMPUP_TIME", "10").toInt seconds
  
  val httpProtocol: HttpProtocolBuilder = http
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
}

object ConfigSupplier extends Configmaster {
  // params
  val baseUrl: String = sys.env.getOrElse("GATLING_BASEURL_SUPPLIERDOMAIN","!ERRORinsertValidBaseURL!").toString
  
  override val httpProtocol: HttpProtocolBuilder = http
    .baseUrl(baseUrl)
    .userAgentHeader("----Driver Gatling Tests for !SUPPLIER!---")
} 

object ConfigOrder extends Configmaster {
  // params
  val baseUrl: String = sys.env.getOrElse("GATLING_BASEURL_ORDERDOMAIN","!ERRORinsertValidORDER_URL!").toString
  
  override val httpProtocol: HttpProtocolBuilder = http
    .baseUrl(baseUrl)
    .userAgentHeader("----Driver Gatling Tests for !ORDER!---")
} 

object ConfigManu extends Configmaster{
  // params
  val baseUrl: String = sys.env.getOrElse("GATLING_BASEURL_MANUFACTUREDOMAIN","!ERRORinsertValidMANU_URL!").toString
  
  override val httpProtocol: HttpProtocolBuilder = http
    .baseUrl(baseUrl)
    .userAgentHeader("----Driver Gatling Tests for !MANUFACTURER!---")
} 