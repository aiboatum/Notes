#include <list>
#include <iostream>
#include <set>
#include <vector>
#include <map>
#include <forward_list>
#include <deque>
#include <array>
int main(){
    std::list<std::string> slist1(1);
    slist1.assign(10,"HIya!");
    for(auto iter :slist1){
        std::cout<<iter<<std::endl;
    }
}