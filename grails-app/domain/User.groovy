

class User {
String firstName
    String lastName
    String email
    String userName
    String password
    String contactNumber
    String profileImageName
    String role
    boolean delFlag
    static constraints = {
        profileImageName nullable: true
    }
}
