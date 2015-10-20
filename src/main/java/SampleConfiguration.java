import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;


public class SampleConfiguration extends Configuration {

    @JsonProperty
    public String URL;

    public String getURL() {
        return URL;
    }

    public void setURL(String url) {
        this.URL = URL;
    }
}
