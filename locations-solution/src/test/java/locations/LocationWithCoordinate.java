package locations;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class LocationWithCoordinate extends TypeSafeMatcher<Location> {

    public static Matcher<Location> locationWithCoordinate(Matcher<Double> matcher){
        return new LocationWithCoordinate(matcher);
    }

    private Matcher<Double> matcher;

    public LocationWithCoordinate(Matcher<Double> matcher) {
        this.matcher = matcher;
    }

    @Override
    protected boolean matchesSafely(Location location) {
        return matcher.matches(location.getLat()) || matcher.matches(location.getLon());
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(" location with coordinate")
                .appendDescriptionOf(matcher);
    }

    @Override
    protected void describeMismatchSafely(Location item, Description mismatchDescription) {
        mismatchDescription
        .appendText(" location with coordinate ")
        .appendValue(item.getLon() + "," + item.getLat());
    }
}
