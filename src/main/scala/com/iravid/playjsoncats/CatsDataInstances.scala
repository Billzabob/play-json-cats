package com.iravid.playjsoncats

import cats.data.{ Chain, NonEmptyChain, NonEmptyList, NonEmptyVector }
import play.api.libs.json.Reads
import play.api.libs.json._

object NonEmptyListInstances extends NonEmptyListInstances
trait NonEmptyListInstances {
  implicit def nonEmptyListReads[A: Reads]: Reads[NonEmptyList[A]] =
    Reads
      .of[List[A]]
      .collect(JsonValidationError("error.expected.nonempty.jsarray"))(
          Function.unlift(NonEmptyList.fromList))
  implicit def nonEmptyListWrites[A: Writes]: Writes[NonEmptyList[A]] =
    Writes.of[List[A]].contramap(_.toList)
}

object ChainInstances extends ChainInstances
trait ChainInstances {
  implicit def chainReads[A: Reads]: Reads[Chain[A]] = Reads.of[List[A]].map(Chain.fromSeq)
  implicit def chainWrites[A: Writes]: Writes[Chain[A]] = Writes.of[List[A]].contramap(_.toList)
}

object NonEmptyChainInstances extends NonEmptyChainInstances
trait NonEmptyChainInstances extends ChainInstances {
  implicit def nonEmptyChainReads[A: Reads]: Reads[NonEmptyChain[A]] =
    Reads
      .of[Chain[A]]
      .collect(JsonValidationError("error.expected.nonempty.jsarray"))(
          Function.unlift(NonEmptyChain.fromChain))
  implicit def nonEmptyChainWrites[A: Writes]: Writes[NonEmptyChain[A]] =
    Writes.of[Chain[A]].contramap(_.toChain)
}

object NonEmptyVectorInstances extends NonEmptyVectorInstances
trait NonEmptyVectorInstances {
  implicit def nonEmptyVectorReads[A: Reads]: Reads[NonEmptyVector[A]] =
    Reads
      .of[Vector[A]]
      .collect(JsonValidationError("error.expected.nonempty.jsarray"))(
          Function.unlift(NonEmptyVector.fromVector))
  implicit def nonEmptyVectorWrites[A: Writes]: Writes[NonEmptyVector[A]] =
    Writes.of[Vector[A]].contramap(_.toVector)
}
