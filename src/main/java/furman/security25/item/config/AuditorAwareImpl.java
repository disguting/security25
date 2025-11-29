package furman.security25.item.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;
import java.util.OptionalInt;

/*
@author   User
@project   security25
@class  AuditorAwareImpl
@version  1.0.0
@since 29.11.2025 - 20.07
*/public class AuditorAwareImpl implements AuditorAware<String> {


    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(System.getProperty("user.name"));
    }
}
