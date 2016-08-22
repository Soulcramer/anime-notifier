package moe.notify.animenotifier.domain.model.anime;


public class Episodes {

    public long max;
    public long available;
    public long watched;
    public long next;
    public long offset;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Episodes)) return false;

        Episodes episodes = (Episodes) o;

        if (max != episodes.max) return false;
        if (available != episodes.available) return false;
        if (watched != episodes.watched) return false;
        if (next != episodes.next) return false;
        return offset == episodes.offset;

    }

    @Override
    public int hashCode() {
        int result = (int) (max ^ (max >>> 32));
        result = 31 * result + (int) (available ^ (available >>> 32));
        result = 31 * result + (int) (watched ^ (watched >>> 32));
        result = 31 * result + (int) (next ^ (next >>> 32));
        result = 31 * result + (int) (offset ^ (offset >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Episodes{" +
                "max=" + max +
                ", available=" + available +
                ", watched=" + watched +
                ", next=" + next +
                ", offset=" + offset +
                '}';
    }
}
