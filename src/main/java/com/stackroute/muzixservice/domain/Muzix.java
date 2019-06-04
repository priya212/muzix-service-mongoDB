package com.stackroute.muzixservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="muzix")
public class Muzix {
    @Id
    @Column(name="trackid")
    private int trackId;
    @Column(name = "trackname")
    private String trackName;
    @Column(name="comments")
    private String comments;
/*

    public Muzix() {
    }

    public Muzix(int trackId, String trackName, String comments) {
        this.trackId = trackId;
        this.trackName = trackName;
        this.comments = comments;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Muzix{" +
                "trackId=" + trackId +
                ", trackName='" + trackName + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
*/
}
