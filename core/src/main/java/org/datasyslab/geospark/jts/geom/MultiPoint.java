/*
 * FILE: MultiPoint
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

import org.locationtech.jts.geom.Geometry;

import java.util.Objects;

/**
 * Wraps {@link org.locationtech.jts.geom.MultiPoint}
 */
public class MultiPoint extends org.locationtech.jts.geom.MultiPoint {

    public static org.locationtech.jts.geom.Point[] getPoints(org.locationtech.jts.geom.GeometryCollection geometry) {
        org.locationtech.jts.geom.Point[] res = new org.locationtech.jts.geom.Point[geometry.getNumGeometries()];
        for (int i = 0; i < res.length; i++)
            res[i] = (org.locationtech.jts.geom.Point) geometry.getGeometryN(i);
        return res;
    }

    private static Point[] convertPoints(org.locationtech.jts.geom.Point[] points) {
        GeometryFactory factory = new GeometryFactory();
        Point[] res = new Point[points.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = (Point)factory.fromJTS(points[i]);
        }
        return res;
    }

    public MultiPoint(Object original) {
        this((org.locationtech.jts.geom.MultiPoint) original);
    }

    public MultiPoint(org.locationtech.jts.geom.MultiPoint original) {
        this(getPoints(original), original.getFactory());
        setUserData(original.getUserData());
    }

    /**
     * {@link org.locationtech.jts.geom.MultiPoint#MultiPoint(org.locationtech.jts.geom.Point[], org.locationtech.jts.geom.GeometryFactory)}
     */
    public MultiPoint(org.locationtech.jts.geom.Point[] points, org.locationtech.jts.geom.GeometryFactory factory) {
        super(convertPoints(points), new GeometryFactory(factory));
        if (getUserData() == null) setUserData("");
    }

    /**
     * Compares to given geometry using {@link org.locationtech.jts.geom.MultiPoint#equals(Geometry)}
     * Also compares userData
     */
    @Override
    public boolean equals(Geometry g) {
        return super.equals(g) && Objects.equals(getUserData(), g.getUserData());
    }

    /**
     * Compares to given geometry using {@link org.locationtech.jts.geom.MultiPoint#equals(Object)}
     * Also compares userData
     */
    @Override
    public boolean equals(Object o) {
        return super.equals(o) && Objects.equals(getUserData(), ((Geometry)o).getUserData());
    }

    /**
     * Produces {@link MultiPoint#toString()} (a WKT representation of the geometry)
     *  , concatenated with the userData string (if exists) as a TSV
     */
    @Override
    public String toString() {
        if (!Objects.equals(getUserData(), ""))
            return super.toString() + "\t" + getUserData();
        return super.toString();
    }
}
