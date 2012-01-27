/*
 * Copyright 2012 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.planner.examples.tsp.swingui;

class LatitudeLongitudeTranslator {

    private double minimumLatitude = Double.MAX_VALUE;
    private double maximumLatitude = -Double.MAX_VALUE;
    private double minimumLongitude = Double.MAX_VALUE;
    private double maximumLongitude = -Double.MAX_VALUE;
    private double latitudeLength = 0.0;
    private double longitudeLength = 0.0;

    private double adjustedWidth = 0.0;
    private double adjustedHeight = 0.0;

    public void addCoordinates(double latitude, double longitude) {
        if (latitude < minimumLatitude) {
            minimumLatitude = latitude;
        }
        if (latitude > maximumLatitude) {
            maximumLatitude = latitude;
        }
        if (longitude < minimumLongitude) {
            minimumLongitude = longitude;
        }
        if (longitude > maximumLongitude) {
            maximumLongitude = longitude;
        }
    }

    public void prepareFor(double width, double height) {
        latitudeLength = maximumLatitude - minimumLatitude;
        longitudeLength = maximumLongitude - minimumLongitude;
        // Keep ratio visually correct
        if (width > height * longitudeLength / latitudeLength) {
            adjustedWidth = height * longitudeLength / latitudeLength;
            adjustedHeight = height;
        } else {
            adjustedWidth = width;
            adjustedHeight = width * latitudeLength / longitudeLength;
        }
    }

    public int translateLongitudeToX(double longitude) {
        return (int) Math.floor((longitude - minimumLongitude) * adjustedWidth / longitudeLength);
    }

    public int translateLatitudeToY(double latitude) {
        return (int) Math.floor((maximumLatitude - latitude) * adjustedHeight / latitudeLength);
    }

    public double translateXToLongitude(int x) {
        return minimumLongitude + (((double) x) * longitudeLength / adjustedWidth);
    }

    public double translateYToLatitude(double y) {
        return maximumLatitude - (((double) y) * latitudeLength / adjustedHeight);
    }

}