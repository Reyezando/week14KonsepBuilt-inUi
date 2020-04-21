package week11;

import java.util.ArrayList;
import java.util.Scanner;

public class Main{
	static ArrayList<Item> listOfItems = new ArrayList<Item>();
	static ArrayList<Payment> listOfPayments = new ArrayList<Payment>();
	static Scanner scanner = new Scanner(System.in);

	public static void seedData()
	{
		listOfItems.add(new Item("Kulkas", "Electronic", 4800000));
		listOfItems.add(new Item("TV", "Electronic", 1280000));
		listOfItems.add(new Item("Laptop", "Computer", 6000000));
		listOfItems.add(new Item("PC", "Computer", 12000000));
	}

	public static void printItem(Item item)
	{
		System.out.println("Nama  : " + item.getName());
		System.out.println("Tipe  : " + item.getType());
		System.out.println("Harga : " + item.getPrice());
	}
	
	public static void printPesanan()
	{
		if(listOfPayments.isEmpty() == true)
		{
			System.out.println("Belum Ada Pesanan");
		}
		else
		{
			for (int i = 0; i < listOfPayments.size(); i++) 
			{
				System.out.println("No \t\t : " + (i + 1));
				System.out.println("Nama \t\t : " + listOfPayments.get(i).getItemName());
				System.out.println("Tipe \t\t : " + listOfPayments.get(i).getItem().getType());
				System.out.println("Status \t\t : " + listOfPayments.get(i).getStatus());
				System.out.println("Sisa Pembayaran  : " + listOfPayments.get(i).getRemainingAmount());
				System.out.println("======================================================");
			}
		}
	}
	
	public static void main(String[] args)
	{
		int pilihan = 0, id = 0, uang, lama_cicil, count = 0;
		String bayar;

		seedData();

		while(true)
		{
			System.out.println("---Program Toko Elektronik---");
			System.out.println("1. Pesan Barang");
			System.out.println("2. Lihat Barang");
			System.out.println("0. Keluar");
			System.out.print("Pilihan : ");
			pilihan = scanner.nextInt();

			if(pilihan == 1) // Pesan Barang
			{
				System.out.println("----Daftar Barang----");

				for(int i = 0; i < listOfItems.size(); i++)
				{
					System.out.println("No    : " + (i + 1));
					printItem(listOfItems.get(i));
					System.out.println("--------------------------------------");
				}

				System.out.println("Pilih : ");
				id = scanner.nextInt();
				
				id--;
				
				printItem(listOfItems.get(id));

				System.out.println("----Tipe Pembayaran----");
				System.out.println("1. Cash");
				System.out.println("2. Credit");
				System.out.print("Pilih : ");
				pilihan = scanner.nextInt();
				
				if(pilihan == 1)
				{
					System.out.print("Bayar (Y/N) : ");
					bayar = scanner.next();
					
					if("Y".equals(bayar) || "y".equals(bayar))
					{
						System.out.println("Harga Pembayaran : " + listOfItems.get(id).getPrice());
						System.out.print("Bayar : ");
						uang = scanner.nextInt();

						if(uang < listOfItems.get(id).getPrice())
						{
							System.out.println("Uang Tidak Mencukupi");
							continue;
						}
						else if(uang > listOfItems.get(id).getPrice())
						{
							System.out.println("Kembalian : " + (uang - listOfItems.get(id).getPrice()));
							
							listOfPayments.add(new Cash(listOfItems.get(id)));
							listOfPayments.get(count).pay(uang);
							
							count++;
							
							System.out.println("Transaksi Telah Dibayar Lunas");
						}
						else
						{
							listOfPayments.add(new Cash(listOfItems.get(id)));
							listOfPayments.get(count).pay(uang);
							
							count++;
							
							System.out.println("Transaksi Telah Lunas");
						}
					}
					else if("N".equals(bayar) || "n".equals(bayar))
					{
						listOfPayments.add(new Cash(listOfItems.get(id)));
						
						count++;
						
						System.out.println("Transaksi Telah Disimpan");
					}
				}
				else if(pilihan == 2)
				{
					while(true) 
					{
						System.out.print("Lama Cicilan (3/6/9/12) : ");
						lama_cicil = scanner.nextInt();
						
						if(lama_cicil == 3 || lama_cicil == 6 || lama_cicil == 9 || lama_cicil == 12)
						{
							break;
						}
						else 
						{
							continue;
						}
					}
										
					System.out.println("Harga Pembayaran : " + listOfItems.get(id).getPrice()/lama_cicil);
					System.out.print("Bayar : ");
					uang = scanner.nextInt();
					
					if(uang == 0 || uang < listOfItems.get(id).getPrice()/lama_cicil)
					{
						continue;
					}
					else if(uang > listOfItems.get(id).getPrice()/lama_cicil)
					{
						System.out.println("Kembalian : " + (uang - listOfItems.get(id).getPrice()/lama_cicil));
					}
					
					listOfPayments.add(new Credit(listOfItems.get(id), lama_cicil));
					listOfPayments.get(count).pay(uang);
					
					count++;
					
					System.out.println("Transaksi Telah Dibayar");					
				}
				else 
				{
					continue;
				}
				
			}
			else if(pilihan == 2) // Lihat Barang
			{
				printPesanan();
				
				if(listOfPayments.isEmpty() == true)
				{
					continue;
				}
				
				System.out.print("Pilih No Transaksi : ");
				pilihan = scanner.nextInt();
				
				if(pilihan == 0) 
				{
					continue;
				}
				
				pilihan--;
				
				if(listOfPayments.get(pilihan).getPaidOff() == true)
				{
					System.out.println("Transaksi Telah Dibayar Lunas");
					continue;
				}
				
				System.out.println("Nama \t\t : " + listOfPayments.get(pilihan).getItemName());
				System.out.println("Tipe \t\t : " + listOfPayments.get(pilihan).getItem().getType());
				System.out.println("Status \t\t : " + listOfPayments.get(pilihan).getStatus());
				System.out.println("Sisa Pembayaran  : " + listOfPayments.get(pilihan).getRemainingAmount());
				System.out.println("Harga Pembayaran : " + listOfPayments.get(pilihan).getRemainingAmount());
				
				while(true)
				{
					System.out.print("Bayar : ");
					uang = scanner.nextInt();
					
					if(uang == 0)
					{
						break;
					}
					
					if(uang < listOfPayments.get(pilihan).getRemainingAmount())
					{
						System.out.println("Uang Tidak Mencukupi");
						
						continue;
					}
					else if(uang > listOfPayments.get(pilihan).getRemainingAmount())
					{
						System.out.println("Kembailan : " + (uang - listOfPayments.get(pilihan).getRemainingAmount()));
						listOfPayments.get(pilihan).pay(uang);
						
						System.out.println("Transaksi Telah Dibayar");
						
						break;
					}
					else
					{
						System.out.println("Transaksi Telah Dibayar");				
						listOfPayments.get(pilihan).pay(uang);
						
						break;
					}
				}
			}
			else if(pilihan == 0) // Keluar
			{
				scanner.close();
				break;
			}
			else
			{
				System.out.println("Salah Input");
				continue;
			}

		}

	}

}
