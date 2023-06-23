import sys
import requests
import json

PR_URL=sys.argv[1].rstrip()

patch_file = requests.get(PR_URL + ".patch")
api_token = sys.argv[2]
headers = {"Content-Type": "application/json", "Authorization": "Bearer " + api_token}
sys_msg = 'You are a programming code change review helper, provide summary for the change to help people understand ' \
          'the change. Do not ' \
          'introduce yourself.'
prompt = 'your task is:' \
         '- Write some summary for the complex functions' \
         '- Use markdown as your response format and highlight any code part' \
         ' You are provided with the code changes (diffs) in a unidiff format.' \
         + patch_file.text

data = {
    'model': 'gpt-3.5-turbo-16k-0613',
    'temperature': 0.35,
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