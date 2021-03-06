/*this programe is doing that:
-----This assignment involves the creation of simple Personal Information Management system 
		that can deal with 4 kinds of items: todo items, notes, appointments and contacts. 
-----我在方法命名是很完整，所以由方法名称就可以知道它的作用		

	@author 吕学民 14130110061 mouron@qq.com
*/


import java.util.Scanner;
import java.lang.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.File; 
import java.io.FileReader;
import java.io.IOException;

public class PIMManager
{
	public static void main(String[ ] args) 
	{
				System.out.println("Welcome to PIM.");
				int ent_num = 0 ;

				Scanner scanner = new Scanner(System.in);
				System.out.println("---Enter a command (suported commands are List Create Save Load Quit)---");
				String Command = " ";
				Command = scanner.next( ) ;
				Pim_enter( Command ,ent_num);
				//System.out.println("List");
						
				//else
				//System.out.println("Your input is inlegal . ");	
	}

	public static void Pim_tips(){
				System.out.println("---Enter a command (suported commands are List Create Save Load Quit)---");
	}

	public static void Pim_item_tips(){
				System.out.println("Enter an item type ( todo, note, contact or appointment )");
	}


	public static void Pim_enter( String  Command ,int ent_num)
	{
			String Comm= " ";
			Comm = Command ;
			String [ ] Commands = {"List",
				"Create", 
				"Save", 
				"Load",
				"Quit" }; //命令列表，打算用switch但是if更顺手

			if(Comm.equalsIgnoreCase(Commands[0]))	{
							Pim_list( ent_num );
							Pim_tips();
							Scanner scanner = new Scanner(System.in);
							Comm = scanner.next( ) ;
							Pim_enter( Comm, ent_num );
					}
				//else
				//		System.out.println("Your input is inlegal . ");
			else if(Comm.equalsIgnoreCase(Commands[1])){
							ent_num = Pim_create( ent_num );
							Scanner scanner = new Scanner(System.in);
							Pim_tips();
							Comm = scanner.next( ) ;
							Pim_enter( Comm , ent_num);
					}

			else if(Comm.equalsIgnoreCase(Commands[2])){
							Pim_save_tips();
							Pim_tips();
							Scanner scanner = new Scanner(System.in);
							Comm = scanner.next( ) ;
							Pim_enter( Comm, ent_num );
					}

			else if(Comm.equalsIgnoreCase(Commands[3])){
							Pim_load(ent_num);
							Pim_tips();
							Scanner scanner = new Scanner(System.in);
							Comm = scanner.next( ) ;
							Pim_enter( Comm, ent_num );
					}

			else if(Comm.equalsIgnoreCase(Commands[4])){
							Pim_quit( );
					}

			else 
				
					{
							System.out.println("Your input is inlegal . ");
							Pim_tips();
							Scanner scanner = new Scanner(System.in);
							Comm = scanner.next( ) ;
							Pim_enter( Comm, ent_num );
					}
	}

	public static void Pim_list( int  ent_num )
	{
		if(ent_num == 0)
		System.out.printf("There are %d items.\n",ent_num);
		else
			Pim_load( ent_num );
		
								
	}

    public static int Pim_create (int  ent_num ) 
	{		
				Pim_item_tips();			
				int NUM = ent_num;
				String Items[ ]  = { "todo", 
					"note", 
					"contact",
					"appointment"};

				String Item_kind = " ";
				Scanner Item_scanner = new Scanner(System.in);
				Item_kind = Item_scanner.next() ;

				if(Item_kind.equalsIgnoreCase(Items[0]))
				{
					PIMTodo todo = new PIMTodo();
					NUM+= todo.todo_num;
					//Item_todo_scanner，每个项目设置对应的scanner，便于计数也便于方法内部使用。
					Scanner Item_todo_scanner = new Scanner(System.in);
					System.out.println("Enter date for todo item:");	
					todo.Data = Item_todo_scanner.nextLine();
					System.out.println("Enter todo text:");
					todo.Fact_Event = Item_todo_scanner.nextLine();
					System.out.println("Enter todo priority:");
					todo.Priority = Item_todo_scanner.next();

					String information = "items" + NUM + " : Todo " + todo.Priority 
						+ " " + todo.Data + " " + todo.Fact_Event + "\n";
					Pim_save(information);

				}

				else if(Item_kind.equalsIgnoreCase(Items[1]))
					{
						PIMNote note = new PIMNote( );
						NUM+= note.note_num;
					//Item_note_scanner，每个项目设置对应的scanner，便于计数也便于方法内部使用。
						Scanner Item_note_scanner = new Scanner(System.in);
						System.out.println("Enter note text:");
						note.Fact_Note  = Item_note_scanner.nextLine();
						System.out.println("Enter note priority:");
						note.Priority = Item_note_scanner.next();

						String information = "items" + NUM + " : Note " 
							+ note.Priority + " " + note.Fact_Note + "\n";
						Pim_save(information);

					}
				
				else if(Item_kind.equalsIgnoreCase(Items[2]))
					{
						PIMContact contact = new PIMContact( );
						NUM += contact.con_num;
					//Item_contact_scanner，每个项目设置对应的scanner，便于计数也便于方法内部使用。
						Scanner Item_contact_scanner = new Scanner(System.in);
						System.out.println("Enter the contacter's name:");
						contact.Name = Item_contact_scanner.next();
						System.out.println("Enter the contacter's family name:");
						contact.Fam_Name = Item_contact_scanner.next();
						System.out.println("Enter the contacter's mail address:");
						contact.Add_Mail = Item_contact_scanner.next();
						System.out.println("Enter contact priority:");
						contact.Priority = Item_contact_scanner.next();

						String information = "items" + NUM + " : Contact " + contact.Priority + " " 
									+ contact.Name + "." +contact.Fam_Name + " " + contact.Add_Mail + "\n";
						Pim_save(information);

					}

				else if(Item_kind.equalsIgnoreCase(Items[3]))
					{
						PIMAppointment appointment = new PIMAppointment( );
						NUM += appointment.app_num;
					//Item_appointment_scanner，每个项目设置对应的scanner，便于计数也便于方法内部使用。
						Scanner Item_appointment_scanner = new Scanner(System.in);
						System.out.println("Enter date for appointment item:");	
						appointment.Data = Item_appointment_scanner.nextLine();
						System.out.println("Enter appointment describtion text:");
						appointment.Des_Event = Item_appointment_scanner.nextLine();
						System.out.println("Enter appointment priority:");
						appointment.Priority = Item_appointment_scanner.next();

						String information = "items" + NUM + " : Appointment " + appointment.Priority 
							+ " " + appointment.Data + " " + appointment.Des_Event + "\n";
						Pim_save(information);

					}
					
				else 
					{
						System.out.println("Your input is inlegal . ");
					}

				return NUM;

	}

	public static void Pim_save_tips(){
			 System.out.println("Items have been saved.");
	}

    public static String Pim_save (String information) {

		//该class的使用参照了常见格式，仅仅做了少量修改
			FileWriter fw = null;
				try {
					fw = new FileWriter("E:/Java_cording/HW_2/list.txt", true);
						fw.write(information) ; 
						fw.flush();
					 } catch (FileNotFoundException e) {
					
						e.printStackTrace();
					 } catch (IOException e) {
				   
						e.printStackTrace();
					 } finally {
						if (fw != null) {
						 try {
								fw.close();
							   } catch (IOException e) {
						   e.printStackTrace();
						}
					}
				}
			return null;
	}

    public static void Pim_load(int ent_num) {
				System.out.println("Loading....... ");
		{
			try {
				System.out.printf("There are %d items.\n",ent_num);
				// 该 class的使用参照了常见格式，仅仅做了少量修改
				FileReader fr = new FileReader("E:/Java_cording/HW_2/list.txt");  
					int ch = 0;  
					while((ch = fr.read()) != -1){  
						System.out.print((char)ch);  
					}  
					fr.close();  
				} catch (IOException e) {

						}
		}
	}

    public static void Pim_quit(  ) {
				System.out.println(" Byebye.......");
			try{
				File f = new File("E:/Java_cording/HW_2/list.txt");
				FileWriter fw =  new FileWriter(f);
				fw.write("");
				fw.close();
			}
			catch (IOException e) {

						}
		}


}

		 abstract class PIMEntity {
			String Priority; 

			PIMEntity() {
				Priority = "normal";
			}

			PIMEntity(String priority) {
				Priority =  priority;
			}
		 		public String getPriority() {
				return Priority;
			}
			// method that changes the priority string
			public void setPriority(String p) {
				Priority = p;
			}

			abstract public void fromString(String s);
			abstract public String toString() ;
		}

		class PIMTodo extends  PIMEntity
		{
			public String Data ;
			String Fact_Event ;
			int todo_num = 0;
			PIMTodo( ) {
							//PIMTodo todo = new PIMTodo( );				
							todo_num++;
			}
			public void fromString(String s)
			{

			}

			public String toString()
			{
					System.out.println("I'am PimTodo");
					return "OK";
			}
		}

			class PIMNote extends  PIMEntity
			{
				String Fact_Note ;
				int note_num = 0;

				PIMNote( )
				{
						note_num++;
				}

				public void fromString(String s)
				{
				
				}

				public String toString()
				{
						System.out.println("I'am PimNote");
						return "OK";
				}


			}

			class PIMAppointment extends  PIMEntity
			{
				String Data ;
				String Des_Event ;
				int app_num = 0;

				PIMAppointment(){
							app_num++;
				}

				public void fromString(String s)
				{
				
				}

				public String toString()
				{
						System.out.println("I'am Pimappointment");
						return "OK";
				}

			}

			class PIMContact extends  PIMEntity
			{
				String Name ;
				String Fam_Name ;
				String Add_Mail ;
				int con_num = 0;

				PIMContact(){
							con_num++;
				}

				public void fromString(String s)
				{
				
				}

				public String toString()
				{
						System.out.println("I'am PimContract");
						return "OK";
				}
			}
