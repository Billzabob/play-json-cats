package com.iravid

package object playjsoncats {
  object implicits
      extends JsResultInstances with JsObjectInstances with JsArrayInstances with FormatInstances
      with ReadsInstances with WritesInstances with NonEmptyListInstances
      with NonEmptyChainInstances with NonEmptyVectorInstances with ChainInstances
}
