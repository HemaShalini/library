public class admin {

        String adminname;
        String password;
        admin(){
            this.adminname="admin";
            this.password="123admin";
        }



        public boolean ValidateAdmin(String name,String pwd){
            if(name.equals(adminname) && password.equals(pwd)){
                return true;
            }
            return false;
        }

}
