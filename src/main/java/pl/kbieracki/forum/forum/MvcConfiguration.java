package pl.kbieracki.forum.forum;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import pl.kbieracki.forum.forum.models.handlers.LoginHandler;

@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    final
    LoginHandler loginHandler;

    @Autowired
    public MvcConfiguration(LoginHandler loginHandler) {
        this.loginHandler = loginHandler;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginHandler);
    }
}
