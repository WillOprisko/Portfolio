/********************************************************
 * OO-Programming in C with Structs
 *   Building Struct Containers for Struct Collections
 *   Using Malloc and Realloc
 *
 * 1. This program focuses on learning how to build
 *    a struct container that holds a dynamic array of
 *    structs named 'collection'.
 *
 * 2. The 'struct collection' ADT encapsulate a dynamic
 *    array of ints.
 *
 * 3. Each declared struct will only possess one data type
 *    in order to focus on demonstrating the fundamentals
 *    for how to create, store, and manipulate data within
 *    struct Abstract Data Types (ADTs).
 *
 * 4. The code will not use 'typedef' to force explicit
 *    declaration, reference, and use of each struct ADT.
 ********************************************************/

#include <stdio.h>
#include <stdlib.h>


//  The struct name 'container' represents the purpose of this ADT for OOP
//  purposes and references its connection to Set Theory.

//  It "contains" only one data structure, a dynamic array of structs
//  named and defined as 'collection'.  The 'struct collection' ADT
//  can possess a dynamic array of ints.
struct container
{
   struct collection *Multisets;
};


//  The struct name 'collection' represents the purpose of the ADT in relation
//  to 'struct container' and references its connection to Set Theory.

//  It encapsulates only one data structure, a dynamic int array called
//  'integers'.
struct collection
{
   int *integers;
};


//    Building this program was for learning, understanding, and demonstrating:
//       1. How to use structs to encapsulate data
//       2. How to store, access, and manipulate data stored within a struct
//       3. How to use malloc and realloc to size and resize dynamic arrays
//       4. How to dynamically add, remove, and display data encapsulated
//          within a struct and an array of structs
int main(void)
{
   int n = 121;
   
   
   // Creating a new 'struct collection' ADT also creates an int * data
   // structure encapsulated within the struct
   struct collection Set1;
   struct collection Set2;
   struct collection Set3;
   struct collection Set4;
   // Malloc memory for each variable
   
   
   // Since Set.integers is a 'int *' data structure , int literals cannot be
   // assigned, unless the exact array location is used
   // (e.g. Set1.intergers[0] = n || Set1.intergers[0] = 379)
   Set1.integers[0] = 379;
   printf("%d\t%d\n", Set1.integers[0], n);      // displays 379 and 121
   // Best practice use malloc to allocate memory before use
   
   
   // The int * 'integers' variable within 'struct collection' Set1 can also
   // have one int * assigned, which requires referencing the variable address.
   Set1.integers = &n;
   
   
   // Since the address was stored in 'int * integers' the data structure
   // must be 'dereferenced' in order to the print the data stored at the
   // memory address location
   printf("%p\t", Set1.integers);               // displays memory address
   printf("%d\t%d\n", *(Set1.integers), n);     // displays 121 and 121
   
   
   // Since the address is stored in 'int * integers', any change to the data
   // associated with the address will impact the data stored in 'int n'
   *(Set1.integers) = 55;
   printf("%d\n", n);                           // displays 55
   
   // And any change to n will impact the data dereferenced in Set1.integers
   n = 717;
   printf("%d\n", *(Set1.integers));            // displays 717
   
   n = 535;
   printf("%d\t%d\n", Set1.integers[0], n);     // displays 535
   
   
   // If the programmer wants to store more than one int literal, then
   // memory on the heap will need to be allocated to the data structure
   // by using 'malloc'
   Set1.integers = (int *) malloc(1 * sizeof(int *));
   // Best practice: check that memory was allocated before writing
   // Always free() memory before using malloc on the same
   
   
   // The previous memory address stored within the int * varible will be lost
   // and the associated data cannot be accessed through dereferencing.
   printf("%p\t%d\t", Set1.integers, *(Set1.integers));
   printf("%d\n", Set1.integers[0]);            // displays 0
   
   // New data can be assigned directly by referring to the index of the array
   Set1.integers[0] = 235;
   printf("%d\n", Set1.integers[0]);            // displays 235
   
   
   
   // Once the array has been allocated memory on the heap, the data structure
   // can be allocated more or less memory by using 'realloc'.
   Set1.integers = (int *) realloc(Set1.integers, 2 * sizeof(int *));
   Set1.integers[1] = 97;
   // Best practice: save the memory addres and then check to make sure that
   //                memory was allocated before writing
   
   
   // If more memory is allocated, then the previously store data will remain
   printf("%d\t", n);
   printf("%d\t", Set1.integers[0]);         // displays 235
   printf("%d\n", Set1.integers[1]);         // displays 97
   
   
   
   // The reallocation of memory and assigning of data to the int * integers
   // can be processed with a for-loop by using a counter variable that
   // serves as the index for the array and the number of memory spaces
   // to allocate
   for (int i = 2; i < 6; i++)   // Can only hold 5 elements 0...4
   {
      Set1.integers = (int *) realloc(Set1.integers, i * sizeof(int *));
      Set1.integers[i] = i + 1;
      printf("%d\n", Set1.integers[i]);
   }
   // Off by one error; either <= 5 (i+1) or < 6 (i)
   // Change sizeof(int *) to sizeof(int)
   // Try to write the for-loop with out '=' to prevent "off by one" errors
   
   
   // Creating a new 'struct container' ADT also creates a 'struct collection *'
   // ADT named 'Multisets' encapsulated within 'Continuum' and an 'int *' named
   // 'integers' within 'Multisets'
   
   // The variable name 'Continuum' was used to represent that the ADT could
   // contain all real integers (within the memory limitations of the computer)
   // and self-document its relationship with Set Theory.
   struct container Continuum;
   
   // Since 'Multisets' is a 'struct collection *' ADT, memory can be allocated
   // to the 'Multisets' within Continuum and made into a dynamic array of
   // 'struct collection' ADTs.
   
   // This provides the opportunity to create an infinite number of 'struct
   // collection' ADTs, all of which can contain an infinite number of integers
   Continuum.Multisets = (struct collection *) malloc(1 * sizeof(struct collection *));
   // Best practice:    sizeof(struct collection)
   
   
   // Once memory from the heap is allocated to 'struct collection * Multisets'
   // within 'Continuum', the dynamic 'int *' arrays created within a declared
   // and initialized 'struct collection' variable (e.g. Set1, Set2, Set3, etc.)
   // can be assigned to specific index locations of 'struct collection *
   // Multisets' that exist within 'struct container Continuum'
   
   // Assignment of a 'struct collection' variable copies the memory address
   // into the specified array index location.  Thus the data within the 'struct
   // collection' array and specified 'struct container -> struct collection'
   // index location are one and the same.
   Set1.integers[0] = 11;
   Continuum.Multisets[0] = Set1;
   printf("%d\t%d\n", Set1.integers[0], Continuum.Multisets[0].integers[0]);
   Continuum.Multisets[0].integers[0] = 33;
   printf("%d\t%d\n", Set1.integers[0], Continuum.Multisets[0].integers[0]);
   printf("%p\t%p\n", Set1.integers, Continuum.Multisets[0].integers);
   
   
   // Memory can be reallocated to 'Continuum.Multisets' in order to increase
   // or decrease that number of 'struct collection' ADTs it can hold.
   Continuum.Multisets = (struct collection *) realloc(Continuum.Multisets, 2 * sizeof(struct collection *));
   
   
   
   // Each index location in the dynamic 'struct collection *' array can
   // contain the same memory address
   Continuum.Multisets[1] = Set1;
   printf("%d\t%d\t%d\n", Set1.integers[0],  Continuum.Multisets[0].integers[0],
                                             Continuum.Multisets[1].integers[0]);
   
   printf("%p\t%p\t%p\n", Set1.integers,  Continuum.Multisets[0].integers,
                                          Continuum.Multisets[1].integers);
   
   
   // The address of a single 'int variable' can also be assigned to the
   // 'int * integers' data structure associated with a 'struct collection' ADT
   // that is contained within the 'struct collection *' array
   Continuum.Multisets[1].integers = &n;
   printf("%d\n", Continuum.Multisets[1].integers[0]);
   printf("%p\t%p\n", &n, Continuum.Multisets[1].integers);
   
   
   // The program can use a for-loop to dynamically allocate memory to each
   // 'int * integers' array associated with each 'struct collection * Multisets
   // array created within 'struct container Contiuum'
   Continuum.Multisets = (struct collection *) realloc(Continuum.Multisets, 5 * sizeof(struct collection *));
   for (int i = 1; i <= 5; i++)
   {
      // The memory allocation moves the address from the stack to the heap
      Continuum.Multisets[i].integers = (int *) malloc(i * sizeof(int *));
   }
   

   // Another for-loop can be used to assign integer values to every 'int *'
   // index location for each int array contained within 'struct collection *'
   for (int i = 0; i < 5; i++)
   {
      Continuum.Multisets[i].integers  = (int *) realloc(Continuum.Multisets[i].integers, 5 * sizeof(int*));
      
      for (int j = 0; j < 5; j++)
      {
         Continuum.Multisets[i].integers[j] = j * i;
      }
   }
   
   
   // The 'struct collection *' index, allocated memory address, and integers
   // contained within can be printed.
   for (int i = 0; i < 5; i++)
   {
      printf("Index %d\t%p\t", i, Continuum.Multisets[i].integers);
      for (int j = 0; j < 5; j++)
      {
         printf("%d ", Continuum.Multisets[i].integers[j]);
      }
      puts("");
   }

   
   
   return 0;
}


/*
 realloc is a relatively expensive call, so you (generally) don't want to extend your buffer one element 
 at a time like I did here. A more common strategy is to pick an initial starting size that covers most 
 cases, and if you need to extend the buffer, double its size.
 
*/

// Shallow copy (referencing the same memory address) vs
// Deep Copy (recursively reallocates memory and then copies the value into the new ADT
// Loop through all multisets; malloc (size)