/*
 * FILE: GeometrySerializer.scala
 * Copyright (c) 2015 - 2019 GeoSpark Development Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.datasyslab.geosparksql.utils

import com.vividsolutions.jts.geom.Geometry
import com.vividsolutions.jts.io.{WKBReader, WKBWriter}
import org.apache.spark.sql.catalyst.util.ArrayData
import com.vividsolutions.jts.io.{WKBReader, WKBWriter}
import org.datasyslab.geospark.jts.geom.GeometryFactory

/**
  * SerDe using the WKB reader and writer objects
  */
object GeometrySerializer {

  private val factory = new GeometryFactory();
  
  /**
    *  Given a geometry returns array of bytes
    * @param geometry JTS geometry
    * @return Array of bites represents this geometry
    */
  def serialize(geometry: Geometry): Array[Byte] = {
    val writer = new WKBWriter(2, 2, true)
    writer.write(geometry)
  }

  /**
    * Given ArrayData returns Geometry
    * @param values ArrayData 
    * @return JTS geometry
    */
  def deserialize(values: ArrayData): Geometry = {
    val reader = new WKBReader()
    factory.fromJTS(reader.read(values.toByteArray()))
  }
}
