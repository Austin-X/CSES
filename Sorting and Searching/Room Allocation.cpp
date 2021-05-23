#include <bits/stdc++.h>
using namespace std;

struct cust { 
  int a, d, idx;
  cust (int a, int d, int idx) {
    this->a = a;
    this->d = d;
    this->idx = idx;
  }
};

bool operator<(cust a, cust b) {
  return a.a < b.a;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  
  int n, ans = 0;
  cin >> n;
  list<cust> arr;
  for (int i = 0; i < n; i ++) {
    int a, b;
    cin >> a >> b;
    arr.push_back(cust(a, b, i));
  }
  arr.sort();

  int res[n];
  multiset<cust> rooms; 
  stack<int> empty;
  list<cust>::iterator it;
  for (it = arr.begin(); it != arr.end(); ++it){
    while (!rooms.empty() && it->a > rooms.begin()->a) {
      empty.push(res[rooms.begin()->idx]);
      rooms.erase(rooms.begin());
    }
    
    if (empty.empty()) {
      ans ++;
      res[it->idx] = ans;
      rooms.insert(cust(it->d, it->a, it->idx));
    } else {
      int val = empty.top();
      empty.pop();
      res[it->idx] = val;
      rooms.insert(cust(it->d, it->a, it->idx));
    }
  }

  cout << ans << endl;
  for (int i = 0; i < n; i ++) cout << res[i] << ' ';
  
  return 0;
}
