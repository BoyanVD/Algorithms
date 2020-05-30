#include <iostream>
#include <string>
#include "Automaton.h"

int main()
{
    Automaton a ({{1, false}, {2, false}, {3, true}, {4, true}, {5, true}});
    a.addTransition(1, 2, 'a', false, false);
    a.addTransition(2, 3, 'b', false, true);
    a.addTransition(3, 4, 'c', false, true);
    a.addTransition(4, 5, 'd', true, true);

    std::string str1 = "abc";
    std::string str2 = "ab";
    std::string str3 = "abcd";

    std::cout << a.isWordFromLanguage(str1) << std::endl;
    std::cout << a.isWordFromLanguage(str2) << std::endl;
    std::cout << a.isWordFromLanguage(str3) << std::endl;
}