package br.com.peixoto.atacadista;

import br.com.peixoto.atacadista.domain.Categoria;
import br.com.peixoto.atacadista.dto.CategoriaRequestDTO;
import br.com.peixoto.atacadista.dto.CategoriaResponseDTO;
import java.time.OffsetDateTime;
import java.util.UUID;

public class FactoryObjects {

    private static final String TEST_STRING = "teste@gmail.com";
    private static final String SIGNATURE_ALGORITHM = "SHA512withRSA";
    private static final String ROOT_CERTIFICATE = "LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUNvRENDQWdtZ0F3SUJBZ0lVZUZoSkZyNmoyZmN0bFdnYUNQL0ZhL0NNdVZvd0RRWUpLb1pJaHZjTkFRRUwKQlFBd1lqRUxNQWtHQTFVRUJoTUNRbEl4Q3pBSkJnTlZCQWdNQWtWVE1SQXdEZ1lEVlFRSERBZFdhWFJ2Y21saApNUkV3RHdZRFZRUUtEQWhDWVc1bGMzUmxjekVoTUI4R0ExVUVBd3dZUW1GdVpYTjBaWE1nVW05dmRDQkRRU0JGCmVHRnRjR3hsTUI0WERUSXpNRE14TURJeE1qY3dOVm9YRFRNek1ETXdOekl4TWpjd05Wb3dZakVMTUFrR0ExVUUKQmhNQ1FsSXhDekFKQmdOVkJBZ01Ba1ZUTVJBd0RnWURWUVFIREFkV2FYUnZjbWxoTVJFd0R3WURWUVFLREFoQwpZVzVsYzNSbGN6RWhNQjhHQTFVRUF3d1lRbUZ1WlhOMFpYTWdVbTl2ZENCRFFTQkZlR0Z0Y0d4bE1JR2ZNQTBHCkNTcUdTSWIzRFFFQkFRVUFBNEdOQURDQmlRS0JnUURkcHg1NmlURjVVTDRGS3M2RFF6V2xvM2RWaEJSUEhzcGIKaEpKSWViTkxZVGxnM2NQSzEydllCRmMxTmN0V0JYSGo1MnIwenNBTGh5OWg0Tk5RUWprbXJQZUZia1lOMUpJVgp3d0xwNS93eTRQaVdBa0NhR2ljREVIUFE2U2xMa3hBdUN5Yk9kVWJ0Mjlad3p2OXBjRmZOaG5xNzZMaVlSZWtCCll3SDFvNWd0M3dJREFRQUJvMU13VVRBZEJnTlZIUTRFRmdRVXhxcTU5UnRBTm1KT3ZwUWFmdUc3WUFjWXdURXcKSHdZRFZSMGpCQmd3Rm9BVXhxcTU5UnRBTm1KT3ZwUWFmdUc3WUFjWXdURXdEd1lEVlIwVEFRSC9CQVV3QXdFQgovekFOQmdrcWhraUc5dzBCQVFzRkFBT0JnUURPT2RIbUh3RllENm9hYzFHc213S2FnTit1cWxtRFVYUUpnY2ljClB0Z2RoZGJPRGc2QXBUZUoyQ0cxKzNld2hrTGlIUWNlVGFtZ1dMZWFzQjBnZk5wdkNtSVlXWHkxWmFBeEtsRHEKaHpnVWNoTlQ4K3ZVVFNBblJWcnAyY0RUNGhnTDMrN09PZ0N2Mm1oYW94MnpvQ1Z6UUNBWjd5aXlBMEFKbEhoaApFMGZrTWc9PQotLS0tLUVORCBDRVJUSUZJQ0FURS0tLS0t";
    private static final String ROOT_PRIVATE_KEY = "LS0tLS1CRUdJTiBQUklWQVRFIEtFWS0tLS0tCk1JSUNlQUlCQURBTkJna3Foa2lHOXcwQkFRRUZBQVNDQW1Jd2dnSmVBZ0VBQW9HQkFOMm5IbnFKTVhsUXZnVXEKem9ORE5hV2pkMVdFRkU4ZXlsdUVra2g1czB0aE9XRGR3OHJYYTlnRVZ6VTF5MVlGY2VQbmF2VE93QXVITDJIZwowMUJDT1Nhczk0VnVSZzNVa2hYREF1bm4vRExnK0pZQ1FKb2FKd01RYzlEcEtVdVRFQzRMSnM1MVJ1M2IxbkRPCi8ybHdWODJHZXJ2b3VKaEY2UUZqQWZXam1DM2ZBZ01CQUFFQ2dZRUF3aVpKVnpIbXlRbkZlQWMzM3RnM0hJQmoKM2Q4VVh2MXNhTjlsdHVBTEFhTUd2R2Rqd2NBUjhhUlRzd01NdWF1SlNBU0R0aTJISUZIb1dnYXhjZUtmNVRRMwo2OFNHRmdTSnh1ZWJyRVB6V1paTitseXkyMzk0d1NaN3JVUzFLWTFSQkZQcFI5R1RSUEdwSU05ZytSY2hBTkE0CnJYVzFFVTg0RTdUbWFid0xRZWtDUVFEMGR1RURoTS9SRG9nV0VnWFFxTWlBYlV2QmtBR3VLdHFTczkyWHlzblIKaGpnNVorTlg1SVlIMWVxaUF0SGJFZ3JoTHRINzZqR1hGRGJnaHhYUnBVTXpBa0VBNkJ5dFhxcHFkOVpsZDNOZQphd1ByKzF2cDBpMUw4OXlVODE3NWNHZlpNM2JiYlpZUitkTFluVjR0UnViczhuc2d2L3dWdmlYdTA3YmdkSWV0CjVMeXFwUUpCQUlVeWNJOTJRYlRxY2dOMGRtNm1SL2lSbElFMmNYUHdMWDlaT1JlSGlrYjdGbzlRcDNJd05VYjMKcXd2eTlWeWpqQUg5ZkxRQmVsdVN6QU95RnBWUHNqa0NRQWZUdjd6cmZqK2dTVGVuK2swZGlyMmxNZE5XbDdNWApneVpaMDR1STFQZzVUOWo0TUk3K0h6UGZVeU9LOGY2VFRGMXRBTWF5dk1VQzY3ckNRSWp3RzhrQ1FRQ29QeWVMCkhnMFdZZzFzYUkxZDQxd1dPdHFHVFVPVktZeE1FbnNLZ2FxcUR0L2RRdEg1YXpXQkQ2L1Z6TVROZkVmb1J1WXMKb1ptSVkwY2t6bWZNVUl0NAotLS0tLUVORCBQUklWQVRFIEtFWS0tLS0t";

    public UUID getUUIDGenericForTest() {
        return UUID.fromString("c715489d-2267-49f1-9eae-5dd98dd7ece4");
    }

    public Categoria getCategoriaGenericForTest() {

        return Categoria.builder()
                .id(1L)
                .descricao("Higiene Pessoal")
                .dataCriacao(OffsetDateTime.now())
                .dataAlteracao(OffsetDateTime.now())
                .build();
    }

    public CategoriaRequestDTO getCategoriaRequestDTOGenericForTest() {

        return CategoriaRequestDTO.builder()
                .descricao("Higiene Pessoal")
                .build();
    }

    public CategoriaResponseDTO getCategoriaResponseDTOGenericForTest() {

        return CategoriaResponseDTO.builder()
                .id(1L)
                .descricao("Higiene Pessoal")
                .dataCriacao(OffsetDateTime.now())
                .dataAlteracao(OffsetDateTime.now())
                .build();
    }

}