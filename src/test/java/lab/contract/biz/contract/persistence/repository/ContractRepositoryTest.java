package lab.contract.biz.contract.persistence.repository;

import lab.contract.biz.contract.persistence.entity.Contract;
import lab.contract.biz.user.persistence.entity.User;
<<<<<<< Updated upstream
import lab.contract.biz.user.presentation.repository.UserRepository;
=======
import lab.contract.biz.user.persistence.repository.UserRepository;
>>>>>>> Stashed changes
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

<<<<<<< Updated upstream
=======
import java.time.LocalDateTime;
>>>>>>> Stashed changes
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ContractRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ContractRepository contractRepository;

    @Test
    public void 계약서_DB연동_테스트() {
        //given
        User user = User.builder()
                .username("홍길동")
                .email("test@test.com")
                .password("1234")
                .privacy_agreement_yn("y")
                .build();
        userRepository.save(user);
        //when
        contractRepository.save(Contract.builder()
                .user(user)
                .contract_name("테스트 계약서")
                .build());
        List<Contract> contracts = contractRepository.findAll();
        //then
        Contract contract = contracts.get(0);
        assertThat(contract.getContract_name()).isEqualTo("테스트 계약서");

    }

}