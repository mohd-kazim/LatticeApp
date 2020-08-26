# LatticeApp
Created application using SqlDatabase
Implemented two methods on two onclick buttons
# On SubmitBtn taken data from user
     public void submitBtn( View view){
          String name= etName.getText().toString().trim();
          String number = etPhone.getText().toString().trim();
          String email= etEmail.getText().toString().trim();
          String address = etAddress.getText().toString().trim();
          String password= etPassword.getText().toString().trim();
          String location = etLocation.getText().toString().trim();
             if(name.length()<4 || number.length()<10 || !email.matches(emailPattern) || address.length()<10
          || !password.matches(passwordPattern)) {
              Toast.makeText(this, "Enter a Valid Content to Submit", Toast.LENGTH_SHORT).show();
          }
         else {
            try {
                DbData db = new DbData(this);
                db.open();
                db.createEntry(name, number, email, address, password, location);
                db.close();
                Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show();
                etName.setText("");
                etPhone.setText("");
                etEmail.setText("");
                etAddress.setText("");
                etPassword.setText("");
                etLocation.setText("");
            } catch (SQLException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
   }
# OnShowData method invoked new activity
   startActivity(new Intent(this,save_data.class));
     
# created PersonData.class custom class for having required everyitem in the list
# created DB class
           public void onCreate(SQLiteDatabase db) {
                String SqlCode = " CREATE TABLE " + DATABASE_TABLE + " (" +
                        KEY_NAME + " TEXT NOT NULL, " + KEY_CELL + " TEXT NOT NULL,"+ KEY_ADDRESS +" TEXT NOT NULL,"+
                        KEY_LOCATION + " TEXT NOT NULL,"+ KEY_PASSWORD +" TEXT NOT NULL,"+ KEY_EMAIL+" TEXT NOT NULL);";
                db.execSQL(SqlCode);  
 # created custom adapter for recyclerview
       public class DcViewHolder extends RecyclerView.ViewHolder {
        ImageView ibLocation;
        TextView tvName1, tvPhone1, tvEmail1,tvAddress1,tvPassword1,tvLocation1;
        public DcViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName1 = itemView.findViewById(R.id.tvName1);
            tvPhone1= itemView.findViewById(R.id.tvPhone1);
            tvEmail1 = itemView.findViewById(R.id.tvEmail1);
            tvAddress1= itemView.findViewById(R.id.tvAddress1);
            tvPassword1 = itemView.findViewById(R.id.tvPassword1);
            tvLocation1= itemView.findViewById(R.id.tvLocation1);
            ibLocation = itemView.findViewById(R.id.ibLocation); 
   # on save data activity with the help of recyclerview data is displayed
     mDatabase = new DbData(this);
           mDatabase.open();
            final ArrayList<PersonData> myData = mDatabase.getData();
            DataClass mAdapter = new DataClass(this, myData);
            contactView.setAdapter(mAdapter);
            mDatabase.close();
