package com.iravid.playjsoncats

import cats.data.{ Chain, NonEmptyChain, NonEmptyList, NonEmptyVector }
import org.scalatest.funsuite.AnyFunSuite
import play.api.libs.json.{ Json, JsError, JsSuccess }

class NonEmptyListInstancesSpec extends AnyFunSuite with NonEmptyListInstances {

  test("Reading an empty list as a NonEmptyList should fail to parse") {
    val result = Json.parse("[]").validate[NonEmptyList[String]]
    assert(result === JsError("error.expected.nonempty.jsarray"))
  }

  test("Reading a non-empty array as a NonEmptyList should parse successfully") {
    val result = Json.parse("""["foo", "bar"]""").validate[NonEmptyList[String]]
    assert(result === JsSuccess(NonEmptyList.of("foo", "bar")))
  }

  test("Reading a non-array as a NonEmptyList should fail to parse") {
    val result = Json.parse("{}").validate[NonEmptyList[String]]
    assert(result === JsError("error.expected.jsarray"))
  }
}

class ChainInstancesSpec extends AnyFunSuite with ChainInstances {

  test("Reading an empty list as a Chain should parse successfuly") {
    val result = Json.parse("[]").validate[Chain[String]]
    assert(result === JsSuccess(Chain.nil))
  }

  test("Reading a non-empty array as a Chain should parse successfully") {
    val result = Json.parse("""["foo", "bar"]""").validate[Chain[String]]
    assert(result === JsSuccess(Chain("foo", "bar")))
  }

  test("Reading a non-array as a Chain should fail to parse") {
    val result = Json.parse("{}").validate[Chain[String]]
    assert(result === JsError("error.expected.jsarray"))
  }
}

class NonEmptyChainInstancesSpec extends AnyFunSuite with NonEmptyChainInstances {

  test("Reading an empty list as a NonEmptyChain should fail to parse") {
    val result = Json.parse("[]").validate[NonEmptyChain[String]]
    assert(result === JsError("error.expected.nonempty.jsarray"))
  }

  test("Reading a non-empty array as a NonEmptyChain should parse successfully") {
    val result = Json.parse("""["foo", "bar"]""").validate[NonEmptyChain[String]]
    assert(result === JsSuccess(NonEmptyChain("foo", "bar")))
  }

  test("Reading a non-array as a NonEmptyChain should fail to parse") {
    val result = Json.parse("{}").validate[NonEmptyChain[String]]
    assert(result === JsError("error.expected.jsarray"))
  }
}

class NonEmptyVectorInstancesSpec extends AnyFunSuite with NonEmptyVectorInstances {

  test("Reading an empty list as a NonEmptyVector should fail to parse") {
    val result = Json.parse("[]").validate[NonEmptyVector[String]]
    assert(result === JsError("error.expected.nonempty.jsarray"))
  }

  test("Reading a non-empty array as a NonEmptyVector should parse successfully") {
    val result = Json.parse("""["foo", "bar"]""").validate[NonEmptyVector[String]]
    assert(result === JsSuccess(NonEmptyVector.of("foo", "bar")))
  }

  test("Reading a non-array as a NonEmptyVector should fail to parse") {
    val result = Json.parse("{}").validate[NonEmptyVector[String]]
    assert(result === JsError("error.expected.jsarray"))
  }
}
