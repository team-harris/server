/**
 * 
 */
package com.cisco.innovation.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author rajagast
 *
 */
@Entity
@Table(name="power")
public class TimeSeriesPowerData {
	@EmbeddedId
	private TimeSeriesPowerPK timeSeriesPowerPK;

	public TimeSeriesPowerPK getTimeSeriesPowerPK() {
		return timeSeriesPowerPK;
	}

	public void setTimeSeriesPowerPK(TimeSeriesPowerPK timeSeriesPowerPK) {
		this.timeSeriesPowerPK = timeSeriesPowerPK;
	}

}
