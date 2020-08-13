/*
 * FILE: LinearRing
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
package org.datasyslab.geospark.jts.geom;

import com.vividsolutions.jts.geom.{CoordinateSequence, GeometryFactory}

/**
 * Wraps {@link com.vividsolutions.jts.geom.LinearRing}
 */
class LinearRing(points: CoordinateSequence, factory: GeometryFactory)
  extends com.vividsolutions.jts.geom.LinearRing(points, factory)
    with Geometry
