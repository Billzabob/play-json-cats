package com.iravid.playjsoncats

import cats.data.NonEmptyList
import com.iravid.playjsoncats.JsResultInstances._
import play.api.libs.json.Reads
import play.api.libs.json.{ Json, JsArray, JsError, JsResult, JsValue, Writes }

object NonEmptyListInstances extends NonEmptyListInstances
trait NonEmptyListInstances {
  implicit def nonEmptyListReads[A: Reads]: Reads[NonEmptyList[A]] = new Reads[NonEmptyList[A]] {
    override def reads(json: JsValue): JsResult[NonEmptyList[A]] = json match {
      case JsArray(values) =>
        values.toList match {
          case head :: tail => NonEmptyList(head, tail).traverse(_.validate[A])
          case Nil          => JsError("Expected a NonEmptyList but got an empty list")
        }
      case other =>
        JsError(s"Expected an array but got $other")
    }
  }

  implicit def nonEmptyListWrites[A: Writes]: Writes[NonEmptyList[A]] =
    new Writes[NonEmptyList[A]] {
      override def writes(nel: NonEmptyList[A]): JsValue = Json.toJson(nel.toList)
    }
}
