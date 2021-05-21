#include <iostream>
#include <algorithm>
using namespace std;

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  
  int n, m, k;
  cin >> n >> m >> k;
  int arr[n], apar[m];
  for (int i = 0; i < n; i ++) cin >> arr[i];
  for (int i = 0; i < m; i ++) cin >> apar[i];
  sort(arr, arr + n);
  sort(apar, apar + m);

  int ans = 0, idx = 0;
  for (int i = 0; i < n; i ++) {
    while (idx < m) {
      if (arr[i] - apar[idx] > k) idx ++;
      else if (apar[idx] - arr[i] > k) break;
      else { ans ++; idx ++; break; }
    }
    if (idx == m) break;
  }
  
  cout << ans;
  return 0;
}
