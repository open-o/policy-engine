/**
 * Copyright 2017 ZTE Corporation.
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
package org.openo.policy.engine.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GenericAlarmEvent {
	
	@JsonProperty
	private String nsId;

	@JsonProperty
	private String objectName;
	
	@JsonProperty
	private String objectType;
	
	@JsonProperty
	private String metricName;
	
	@JsonProperty
	private double metricValue;
	
	@JsonProperty
	private long timestamp;

	public GenericAlarmEvent() {
	}
	
	public String getNsId() {
		return nsId;
	}

	public void setNsId(String nsId) {
		this.nsId = nsId;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjecttype(String objectType) {
		this.objectType = objectType;
	}

	public String getMetricName() {
		return metricName;
	}

	public void setMetricName(String metricName) {
		this.metricName = metricName;
	}

	public double getMetricValue() {
		return metricValue;
	}

	public void setMetricValue(double metricValue) {
		this.metricValue = metricValue;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}


}
