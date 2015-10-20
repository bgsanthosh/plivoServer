import com.plivo.PostResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.filter.LoggingFilter;

import java.util.logging.Logger;


public class SampeMainApplication extends Application<SampleConfiguration> {

    public static void main(String[] args) throws Exception {
        new SampeMainApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<SampleConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(SampleConfiguration configuration,
                    Environment environment) {
        final PostResource resource = new PostResource();

        environment.jersey().register(resource);
        environment.jersey().register(new LoggingFilter(
                        Logger.getLogger(LoggingFilter.class.getName()),
                        true)
        );

    }
}
