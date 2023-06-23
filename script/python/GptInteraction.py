import sys
import requests
import json

PR_URL=sys.argv[1].rstrip()

patch_file = requests.get(PR_URL + ".patch")
api_token = sys.argv[2]
headers = {"Content-Type": "application/json", "Authorization": "Bearer " + api_token}
sys_msg = 'You are a programming code change reviewer, provide feedback on the code changes given. Do not ' \
          'introduce yourself.'
prompt = 'your task is:' \
         '- Review the code changes and provide feedback.' \
         '- Write some summary for the complex functions' \
         '- If there are any bugs, highlight them.' \
         '- Provide details on missed use of best-practices.' \
         '- Check if there is any debug print should be removed' \
         '- Check if there any infinite loop' \
         '- Check if there any unreachable code' \
         '- Does the code do what it says in the commit messages?' \
         '- Do not highlight minor issues and nitpicks.' \
         '- Use bullet points if you have multiple comments.' \
         '- Provide security recommendations if there are any.' \
         '-use markup as your response format and highlight any code part' \
         'You are provided with the code changes (diffs) in a unidiff format.' \
         + patch_file.text
data = {
    'model': 'gpt-3.5-turbo-16k-0613',
    'temperature': 0.1,
    'messages': [
        {
            'role': 'system',
            'content': sys_msg
        },
        {
            'role': 'user',
            'content': prompt
        }
    ]
}

response = requests.post('https://api.openai.com/v1/chat/completions', data=json.dumps(data), headers=headers).json()

print(response['choices'][0]['message']['content'])