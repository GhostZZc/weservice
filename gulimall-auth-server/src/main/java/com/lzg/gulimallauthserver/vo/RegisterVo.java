package com.lzg.gulimallauthserver.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @ClassName: RegisterVo
 * @Description: 注册Vo
 * @author: lzg
 * @date: 2023/6/13 10:44
 */
@Data
public class RegisterVo implements Serializable {
    @NotEmpty(message = "用户名不能为空")
    @Length(min =4,max = 18,message = "用户名必须是6~18位字符")
    private String userName;
    @NotEmpty(message = "密码不能为空")
    @Length(min =6,max = 18,message  = "密码必须是6~18位字符")
    private String password;
    @NotEmpty(message = "手机号不能为空")
    @Pattern(regexp = "^[1]([3-9])[0-9]{9}$",message = "手机号码格式不正确")
    private String phone;
    @NotEmpty(message = "验证码不能为空")
    private String code;


}
