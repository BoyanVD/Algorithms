#ifndef __AUTOMATON_H
#define __AUTOMATON_H

#include <iostream>
#include <string>
#include <vector>

struct Node;

struct Transition
{
    Node* node;
    char label;

    Transition(Node* n, char l) : node(n), label(l) {}
};

struct Node
{
    unsigned id;
    std::vector<Transition> neighbours;
    bool isFinal;

    Node(unsigned _id, bool isFinalState) : id(_id), isFinal(isFinalState) {}

    std::vector<Node*> findAllNeighboursWithTransition(char letter)
    {
        std::vector<Node*> result;
        for (Transition neighbour : this->neighbours)
        {
            if (neighbour.label == letter)
            {
                result.push_back(neighbour.node);
            }
        }
        return result;
    }
};

class Automaton
{
private:
    std::vector<Node*> allNodes;
    std::vector<Node*> startingNodes;
public:
    Automaton(std::vector<std::pair<unsigned, bool>> startNodesIndexes)
    {
        for (std::pair<unsigned, bool> n : startNodesIndexes)
        {
            Node* node = new Node(n.first, n.second);// Trqbwa da se pomisli
            this->allNodes.push_back(node);
            this->startingNodes.push_back(node);
        }
    }

    ~Automaton()
    {
        for(Node* n : allNodes)
            delete n;
    }

    Node* getNodeByIndex(unsigned nodeId)
    {
        for (Node* n : allNodes)
        {
            if (n->id == nodeId)
                return n;
        }
        return nullptr;
    }

    void addTransition(unsigned from, unsigned to, char label, bool isFromFinal, bool isToFinal)
    {
        Node* transitionBegin = getNodeByIndex(from);        
        Node* transitionEnd = getNodeByIndex(to);

        Transition t(transitionEnd, label);
        transitionBegin->neighbours.push_back(t);
    }

    bool isThereFinalState(const std::vector<Node*>& nodes)
    {
        for (Node* n : nodes)
        {
            if (n->isFinal)
                return true;
        }
        return false;
    }

    bool canWeGetToEnd(Node* n, const std::string& word, size_t position)
    {

        if (position >- word.length())
            return false;

        std::vector<Node*> neighbours = n->findAllNeighboursWithTransition(word[position]);

        if (neighbours.size() == 0)
            return false;
        if (isThereFinalState(neighbours) && position == (word.length() - 1))
            return true;

        for (Node* n : neighbours)
        {
            if (canWeGetToEnd(n, word, position + 1))
                return true;
        }
        return false;
    }

    bool isWordFromLanguage(const std::string& word)
    {
        for (Node* n : startingNodes)
        {
            if (canWeGetToEnd(n, word, 0))
                return true;
        }
        return false;
    }
};

#endif