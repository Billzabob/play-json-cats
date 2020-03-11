package com.iravid.playjsoncats

import cats.data.NonEmptyList
import org.scalatest.FunSuite
import play.api.libs.json.{ Json, JsError, JsSuccess }

class CatsDataInstancesSpec extends FunSuite with NonEmptyListInstances {

  test("Reading an empty list as a NonEmptyList should fail to parse") {
    val result = Json.parse("[]").validate[NonEmptyList[String]]
    assert(result === JsError("Expected a NonEmptyList but got an empty list"))
  }

  test("Reading a non-empty array as a NonEmptyList should parse successfully") {
    val result = Json.parse("""["foo", "bar"]""").validate[NonEmptyList[String]]
    assert(result === JsSuccess(NonEmptyList.of("foo", "bar")))
  }

  test("Reading a non-array as a NonEmptyList should fail to parse") {
    val result = Json.parse("{}").validate[NonEmptyList[String]]
    assert(result === JsError("Expected an array but got {}"))
  }
}
