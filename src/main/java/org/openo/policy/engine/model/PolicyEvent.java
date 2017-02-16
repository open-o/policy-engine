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

public class PolicyEvent {

	@JsonProperty
	protected String objectName;
	
	@JsonProperty
	protected String objectType;
	
	@JsonProperty
	protected String metricName;
	
	@JsonProperty
	protected double metricValue;
	
	@JsonProperty
	protected long timestamp;

	public PolicyEvent() {
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

	@Override
	public String toString() {
		return "PolicyEvent{" + "objectName='" + objectName + '\'' + ", objectType='" + objectType + '\''
				+ ", metricName='" + metricName + '\'' + ", metricValue=" + metricValue + ", timestamp=" + timestamp
				+ '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		PolicyEvent that = (PolicyEvent) o;
		if (!objectName.equals(that.objectName))
			return false;
		if (!objectType.equals(that.objectType))
			return false;
		return metricName.equals(that.metricName);

	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = objectName != null ? objectName.hashCode() : 0;
		result = 31 * result + (objectType != null ? objectType.hashCode() : 0);
		result = 31 * result + (metricName != null ? metricName.hashCode() : 0);
		temp = Double.doubleToLongBits(metricValue);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
		return result;
	}

}
