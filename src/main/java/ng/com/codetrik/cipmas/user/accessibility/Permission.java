package ng.com.codetrik.cipmas.user.accessibility;
/*
 @Author Hamzat Habibllahi
 */
public enum Permission {
    READ("auth:read"),
    WRITE("auth:write"),
    UPDATE("auth:update"),
    DELETE("auth:delete"),
    SPECIAL("auth:special");

    private final String authority;

    Permission(String authority){
        this.authority = authority;
    }
}
