<template>
    <div id="app">
        <div class="row">
            <div class="col s3">
                <conversations-list v-on:switch-conversation="name = $event"/>
            </div>
            <div class="col s5" style="overflow">
                <conversation :name=name />
            </div>
        </div>
    </div>
</template>
<script>
    import ConversationList from '@/components/ConversationsList'
    import Conversation from '@/components/Conversation'
    import 'materialize-css'
    import 'materialize-css/dist/css/materialize.min.css'
    import 'material-icons'

    export default {
        name: "app",
        components: {
            'conversations-list': ConversationList,
            'conversation': Conversation
        },
        data() {
            return {
                name: null
            }
        }
    }
</script>