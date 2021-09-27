package syntax

import "fmt"

/**
 *	函数和方法的区别
 */
type Myint int

func (i *Myint) mydouble() int {
	*i = *i * 2
	return 0
}

func (i Myint) mysquare() {
	i = i * i
	fmt.Println("square=", i)
}

func TestFunc() {
	var a Myint = 2
	a.mydouble()
	fmt.Println(a)
	a.mysquare()
	fmt.Println(a)
}
