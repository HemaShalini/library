public class Admin {

        String adminname;
        String password;
        Admin(){
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
