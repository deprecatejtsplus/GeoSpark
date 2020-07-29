/*
 * FILE: CRSTransformation
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
package org.datasyslab.geospark.utils;

import org.locationtech.jts.geom.Geometry;

import java.io.Serializable;
import java.util.Objects;

public final class ComparableGeometry<T extends Geometry> implements Serializable {

    private T geometry;

    public ComparableGeometry(T geometry) {
        this.geometry = geometry;
    }

    @Override
    public int hashCode() {
        return geometry.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ComparableGeometry)) {
            return false;
        }
        ComparableGeometry o = (ComparableGeometry) other;
        return Objects.equals(geometry, o.geometry) && Objects.equals(geometry.getUserData(), o.geometry.getUserData());
    }

    public T getGeometry() {
        return geometry;
    }
}