package modules.journey.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Table;

import java.time.LocalDateTime;


@Table(name = "journey")
public class Journey {

    public Journey() {}

    @Column(name = "departure")
    private LocalDateTime departure;

    @Column(name = "arrival")
    private LocalDateTime arrival;

    @Column(name = "departure_station_id")
    private Integer departureStationId;

    @Column(name = "departure_station_name")
    private String departureStationName;

    @Column(name = "return_station_id")
    private Integer arrivalStationId;

    @Column(name = "return_station_name")
    private String arrivalStationName;

    @Column(name = "distance_covered")
    private Integer distanceCovered;

    @Column(name = "duration")
    private Integer duration;

    public LocalDateTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }

    public Integer getDepartureStationId() {
        return departureStationId;
    }

    public void setDepartureStationId(Integer departureStationId) {
        this.departureStationId = departureStationId;
    }

    public String getDepartureStationName() {
        return departureStationName;
    }

    public void setDepartureStationName(String departureStationName) {
        this.departureStationName = departureStationName;
    }

    public Integer getArrivalStationId() {
        return arrivalStationId;
    }

    public void setArrivalStationId(Integer arrivalStationId) {
        this.arrivalStationId = arrivalStationId;
    }

    public String getArrivalStationName() {
        return arrivalStationName;
    }

    public void setArrivalStationName(String arrivalStationName) {
        this.arrivalStationName = arrivalStationName;
    }

    public Integer getDistanceCovered() {
        return distanceCovered;
    }

    public void setDistanceCovered(Integer distanceCovered) {
        this.distanceCovered = distanceCovered;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
