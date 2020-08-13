/*
 * FILE: Point
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
package org.datasyslab.geospark.jts.geom

import java.util.Objects

/**
 * Wraps {@link com.vividsolutions.jts.geom.Geometry}
 */
trait Geometry extends com.vividsolutions.jts.geom.Geometry {
  if (getUserData == null) setUserData("")

  /**
   * Compares to given geometry using {@link com.vividsolutions.jts.geom.Geometry#equals(Geometry)}
   * Also compares userData
   */
  def equals(g: Geometry): Boolean = super.equals(g) && Objects.equals(getUserData, g.getUserData)

  /**
   * Compares to given geometry using {@link com.vividsolutions.jts.geom.Geometry#equals(Object)}
   * Also compares userData
   */
  override def equals(o: Object): Boolean = o match {
    case g: Geometry => super.equals(o) && Objects.equals(getUserData, g.getUserData)
    case _ => false
  }

  /**
   * Produces {@link com.vividsolutions.jts.geom.Geometry#toString()} (a WKT representation of the geometry)
   * , concatenated with the userData string (if exists) as a TSV
   */
  override def toString: String = super.toString + (if (!Objects.equals(getUserData, "")) "\t" + getUserData else "")
}

object Geometry {
  import scala.reflect.ClassTag
  private def getGeometries[T <: com.vividsolutions.jts.geom.Geometry](geometry: com.vividsolutions.jts.geom.GeometryCollection) =
    (0 to geometry.getNumGeometries).map(geometry.getGeometryN).toArray.asInstanceOf[Array[T]]

  implicit def wrap(original: org.datasyslab.geospark.geometryObjects.Circle): Circle =
    new Circle(original.getCenterGeometry, original.getRadius)

  implicit def wrap(original: com.vividsolutions.jts.geom.GeometryCollection): GeometryCollection =
    new GeometryCollection(getGeometries(original), original.getFactory)

  implicit def wrap(original: com.vividsolutions.jts.geom.LinearRing): LinearRing =
    new LinearRing(original.getCoordinateSequence, original.getFactory)

  implicit def wrap(original: com.vividsolutions.jts.geom.LineString): LineString =
    new LineString(original.getCoordinateSequence, original.getFactory)

  implicit def wrap(original: com.vividsolutions.jts.geom.MultiLineString): MultiLineString =
    new MultiLineString(getGeometries(original), original.getFactory)

  implicit def wrap(original: com.vividsolutions.jts.geom.MultiPoint): MultiPoint =
    new MultiPoint(getGeometries(original), original.getFactory)

  implicit def wrap(original: com.vividsolutions.jts.geom.MultiPolygon): MultiPolygon =
    new MultiPolygon(getGeometries(original), original.getFactory)

  implicit def wrap(original: com.vividsolutions.jts.geom.Point): Point =
    new Point(original.getCoordinateSequence, original.getFactory)

  implicit def wrap(original: com.vividsolutions.jts.geom.Polygon): Polygon = {
    val holes = (1 to original.getNumInteriorRing).map(original.getInteriorRingN)
    new Polygon(original.getExteriorRing.asInstanceOf, holes.asInstanceOf, original.getFactory)
  }
}
