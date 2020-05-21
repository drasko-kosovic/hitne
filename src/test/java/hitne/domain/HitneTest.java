package hitne.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hitne.web.rest.TestUtil;

public class HitneTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Hitne.class);
        Hitne hitne1 = new Hitne();
        hitne1.setId(1L);
        Hitne hitne2 = new Hitne();
        hitne2.setId(hitne1.getId());
        assertThat(hitne1).isEqualTo(hitne2);
        hitne2.setId(2L);
        assertThat(hitne1).isNotEqualTo(hitne2);
        hitne1.setId(null);
        assertThat(hitne1).isNotEqualTo(hitne2);
    }
}
