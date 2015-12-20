package org.h6.domain.task.status;

import javax.persistence.*;

@Entity
@Table(name = "status_transitions")
public class StatusTransition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "from_id", nullable = false)
    private StatusItem from;

    @OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "to_id", nullable = false)
    private StatusItem to;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StatusItem getFrom() {
        return from;
    }

    public void setFrom(StatusItem from) {
        this.from = from;
    }

    public StatusItem getTo() {
        return to;
    }

    public void setTo(StatusItem to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StatusTransition that = (StatusTransition) o;

        if (id != that.id) return false;
        if (!from.equals(that.from)) return false;
        return to.equals(that.to);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + from.hashCode();
        result = 31 * result + to.hashCode();
        return result;
    }
}
