package moe.notify.animenotifier.domain.model.anime;


public class AiringDate {

    public String remainingString;
    public long timeStamp;
    public long remaining;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AiringDate)) return false;

        AiringDate that = (AiringDate) o;

        if (timeStamp != that.timeStamp) return false;
        if (remaining != that.remaining) return false;
        return remainingString != null ? remainingString.equals(that.remainingString) : that.remainingString == null;

    }

    @Override
    public int hashCode() {
        int result = remainingString != null ? remainingString.hashCode() : 0;
        result = 31 * result + (int) (timeStamp ^ (timeStamp >>> 32));
        result = 31 * result + (int) (remaining ^ (remaining >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "AiringDate{" +
                "remainingString='" + remainingString + '\'' +
                ", timeStamp=" + timeStamp +
                ", remaining=" + remaining +
                '}';
    }
}
