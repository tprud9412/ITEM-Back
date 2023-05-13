package kit.item.dto.response.member;

import kit.item.dto.entity.member.MemberInfoDto;
import kit.item.enums.RoleType;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ResponseGetMemberInfoDto {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String phoneNumber;
    private String address;
    private Long point;
    private RoleType roleType;

    public static ResponseGetMemberInfoDto to(MemberInfoDto memberDto){
        return ResponseGetMemberInfoDto.builder()
                .id(memberDto.getId())
                .email(memberDto.getEmail())
                .password(memberDto.getPassword())
                .name(memberDto.getName())
                .nickname(memberDto.getNickname())
                .phoneNumber(memberDto.getPhoneNumber())
                .address(memberDto.getAddress())
                .point(memberDto.getPoint())
                .roleType(memberDto.getRoleType())
                .build();
    }
}