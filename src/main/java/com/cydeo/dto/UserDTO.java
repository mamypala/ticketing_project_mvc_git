package com.cydeo.dto;

import com.cydeo.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    /* Validation ->
        1- DTO da annotation ları ekle (her DTO için ayrı ayrı yapılcak)
        2- İlgili Controller a git -> her @PostMapping annotation olan yerde
           formdan gelen user(veya diğer alanlar) @Valid annotation ekle
        3- resource un içine yeni messages.properties belgesi oluşturulcak.
        4- Bir sorun varsa(Validation la ilgili) sorunu yakalayabilmemiz lazım. Bunun için
           BindingResult interface i kullanıyoruz.
           Kural olarak @Valid edilecek objeden hemen sonra yazılması şart!
           bir if() - bloğu açılarak "eğer sorun varsa aynı sayfada kal ve gerekli bilgileri de sağla" denir.

    */

    @NotBlank                   // Validation
    @Size(max = 15, min = 2)
    private String firstName;

    @NotBlank
    @Size(max = 15, min = 2)
    private String lastName;

    @NotBlank
    @Email
    private String userName;

    @NotBlank
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,}")
    private String passWord;


    private boolean enabled;

    @NotBlank
    @Pattern(regexp = "^\\d{10}$")
    private String phone;

    @NotNull
    private RoleDTO role;

    @NotNull
    private Gender gender;


}
